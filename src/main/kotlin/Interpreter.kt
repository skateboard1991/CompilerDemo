import java.lang.RuntimeException

class Interpreter(private val parser: Parser) {
    fun interpret():Int {
        val ast = parser.parse()
        return visit(ast)
    }

    private fun visit(ast: AST): Int {
        when (ast) {
            is BinOp -> {
                return when (ast.op.tokenType) {
                    TokenType.PLUS -> {
                        visit(ast.left) + visit(ast.right)
                    }
                    TokenType.MIN -> {
                        visit(ast.left) - visit(ast.right)
                    }
                    TokenType.MUL -> {
                        visit(ast.left) * visit(ast.right)
                    }
                    TokenType.DIV -> {
                        visit(ast.left) / visit(ast.right)
                    }
                    else -> {
                        throw RuntimeException("error ast")
                    }
                }
            }
            is Num -> {
                return ast.token.value.toInt()
            }
            else -> {
                throw RuntimeException("error ast")
            }
        }
    }
}
import java.lang.RuntimeException

class Parser(val lexer: Lexer) {

    private var curToken: Token = lexer.getNextToken()

    fun parse(): AST = exp()

    private fun eat(tokenType: TokenType) {
        if (tokenType == curToken.tokenType) {
            curToken = lexer.getNextToken()
        } else {
            throw RuntimeException("TokenType是${tokenType.value}语法格式错误")
        }
    }

    private fun exp(): AST {
        var node = term()
        while (TokenType.MIN == curToken.tokenType || TokenType.PLUS == curToken.tokenType) {
            val tmpToken = curToken
            if (TokenType.MIN == curToken.tokenType) {
                eat(TokenType.MIN)
            } else {
                eat(TokenType.PLUS)
            }
            node = BinOp(node, tmpToken, term())
        }
        return node
    }

    fun term(): AST {
        var node = factor()
        while (TokenType.MUL == curToken.tokenType || TokenType.DIV == curToken.tokenType) {
            val tmpToken = curToken
            if (TokenType.MUL == curToken.tokenType) {
                eat(TokenType.MUL)
            } else if (TokenType.DIV == curToken.tokenType) {
                eat(TokenType.DIV)
            }
            node = BinOp(node, tmpToken, factor())
        }
        return node
    }

    fun factor(): AST {
        val tmpToken = curToken
        return when {
            TokenType.NUMBER == curToken.tokenType -> {
                eat(TokenType.NUMBER)
                Num(tmpToken)
            }
            TokenType.LBRACKETS == curToken.tokenType -> {
                eat(TokenType.LBRACKETS)
                val node = exp()
                eat(TokenType.RBRACKETS)
                node
            }
            else -> {
                throw RuntimeException("TokenType是${curToken.value}语法格式错误")
            }
        }
    }

}
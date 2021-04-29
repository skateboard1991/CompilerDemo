import java.lang.StringBuilder

open class AST {

}

class BinOp(val left: AST, val op: Token, val right: AST) : AST() {

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(op.value).append("\n").append(left.toString()).append(" ").append(right.toString())
        return sb.toString()
    }
}


class Num(val token: Token) : AST() {

    override fun toString(): String = token.value
}
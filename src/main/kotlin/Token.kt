data class Token(val tokenType: TokenType, val value: String) {
    override fun toString(): String {
        return "Token(tokenType=${this.tokenType.value}, value= ${this.value})"
    }
}


enum class TokenType(val value: String) {
    NUMBER("NUMBER"), PLUS("+"), MIN("-"), MUL("*"), DIV("/"), LBRACKETS("("), RBRACKETS(")"), EOF("EOF")
}


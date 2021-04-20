import java.lang.RuntimeException

class Lexer(private val text: String) {

    private var nextPos = 0

    private val tokenMap = mutableMapOf<String, TokenType>()

    private var nextChar: Char? = null

    init {
        TokenType.values().forEach {
            if(it!=TokenType.NUMBER) {
                tokenMap[it.value] = it
            }
        }
        nextChar = text.getOrNull(nextPos)
    }

    fun getNextToken(): Token {
        loop@ while (nextChar != null) {
            when (nextChar) {
                in '0'..'9' -> {
                    return Token(TokenType.NUMBER, getNumber())
                }
                ' ' -> {
                    skipWhiteSpace()
                    continue@loop
                }
            }
            if (tokenMap.containsKey(nextChar.toString())) {
                val tokenType = tokenMap[nextChar.toString()]
                if (tokenType != null) {
                    val token = Token(tokenType, tokenType.value)
                    advance()
                    return token
                }
            }
            throw RuntimeException("Error parsing input")
        }
        return Token(TokenType.EOF, TokenType.EOF.value)
    }

    private fun getNumber(): String {
        var item = ""
        while (nextChar != null && nextChar!! in '0'..'9') {
            item += nextChar
            advance()
            if ('.' == nextChar) {
                item += nextChar
                advance()
                while (nextChar != null && nextChar!! in '0'..'9') {
                    item += nextChar
                    advance()
                }
            }
        }
        return item
    }

    private fun skipWhiteSpace() {
        var nextChar = text.getOrNull(nextPos)
        while (nextChar != null && ' ' == nextChar) {
            advance()
            nextChar = text.getOrNull(nextPos)
        }
    }

    private fun advance() {
        nextPos++
        nextChar = if (nextPos > text.length - 1) {
            null
        } else {
            text.getOrNull(nextPos)
        }
    }

}
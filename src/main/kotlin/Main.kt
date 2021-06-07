import java.util.*

fun main() {
    while (true) {
        val scanner = Scanner(System.`in`)
        val text = scanner.nextLine()
        val lexer = Lexer(text)
//        var nextToken = lexer.getNextToken()
//        while (TokenType.EOF != nextToken.tokenType) {
//            println(nextToken.toString())
//            nextToken = lexer.getNextToken()
//        }
        val parser = Parser(lexer)
        val interpreter=Interpreter(parser)
        println("result is ${interpreter.interpret()}")
    }
}
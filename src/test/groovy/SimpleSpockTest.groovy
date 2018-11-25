import spock.lang.Specification

class SimpleSpockTest extends Specification {

    def "simpleTest"() {
        expect:
        println("Spock Test works")
    }
}

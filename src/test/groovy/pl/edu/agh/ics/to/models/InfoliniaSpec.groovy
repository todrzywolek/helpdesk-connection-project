package pl.edu.agh.ics.to.models

import spock.lang.Specification

class InfoliniaSpec extends Specification {

    def dep1 = new Dzial("test_department_1", 1)
    def dep2 = new Dzial("test_department_2", 2)

    Queue<Polaczenie> pendingCalls = Mock()
    def infolinia = new Infolinia([dep1, dep2], pendingCalls)

    def "should throw exception when redirecting to invalid department"() {
        given: "connection to invalid department"
        def invalidConnection = new Polaczenie(1, 3)

        when: "redirecting connection to invalid department"
        infolinia.przekazDoDzialu(invalidConnection)

        then: "exception is thrown"
        IllegalArgumentException e = thrown()
        e.getMessage() == "Niewlasciwy numer dzialu"
    }

    def "should add connection to queue when redirecting connection to blocked department"() {
        given: "served connection"
        def servedConnection = new Polaczenie(1, 2)
        servedConnection.setZakonczone(false)

        and: "blocked department"
        infolinia.getDzialy().get(1).setPolaczenie(servedConnection)

        and: "new connection that wants to connect to locked department"
        def newConnection = new Polaczenie(2, 2)

        when: "redirecting to department"
        def result = infolinia.przekazDoDzialu(newConnection)

        then: "connection is added to pending connection queue"
        1 * pendingCalls.add(newConnection)

        and: "redirect result is false"
        !result
    }

    def "should redirect connection when department is not blocked"() {
        given: "mocked department added to department collection"
        Dzial dep3 = Mock()
        infolinia.getDzialy().add(dep3)

        and: "set mocked dept return statements"
        final deptNum = 3
        1 * dep3.czyZajete() >> false
        1 * dep3.getNrWewnetrzny() >> deptNum

        and: "new connection to mocked department"
        def newConnection = new Polaczenie(1, deptNum)

        when: "redirecting to department"
        def result = infolinia.przekazDoDzialu(newConnection)

        then: "department is answering the call"
        1 * dep3.odbierzPolaczenie(newConnection)

        and: "redirect result is true"
        result
    }
}

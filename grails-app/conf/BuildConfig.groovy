grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.repos.default = "quonb-snapshot"

grails.project.dependency.distribution = {
    String serverRoot = "http://mvn.quonb.org"
    remoteRepository(id: 'quonb-snapshot', url: serverRoot + '/plugins-snapshot-local/')
    remoteRepository(id: 'quonb-release', url: serverRoot + '/plugins-release-local/')
}

grails.project.dependency.resolution = {
    inherits "global"
    log "info" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    legacyResolve true
    repositories {
        grailsCentral()
        mavenCentral()
        mavenLocal()
        mavenRepo "http://mvn.quonb.org/repo"
        mavenRepo "http://files.couchbase.com/maven2/"
        grailsRepo "http://mvn.quonb.org/repo", "quonb"

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        test("org.spockframework:spock-grails-support:0.7-groovy-2.0") {
            export = false
        }
    }

    plugins {
        build(":tomcat:$grailsVersion",
                ":release:2.2.1") {
            export = false
        }
        compile ":hibernate:$grailsVersion"
        compile ":shiro:1.1.4"
        test(":spock:0.7") {
            exclude "spock-grails-support"
            export = false
        }
    }
}

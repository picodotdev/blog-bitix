package es.com.blogspot.elblogdepicodev.test.geb

import geb.Page
import geb.spock.GebSpec
 
class GoogleHomePage extends Page {
    static url = 'http://google.es/'
    static at = { title == 'Google' }
    static content = {
        searchField { $("input[name=q]") }
        searchButton(to: GoogleResultsPage) { $("input[value='Buscar con Google']") }
    }
}

class GoogleResultsPage extends Page {
    static at = { waitFor { title.endsWith("Buscar con Google") } }
    static content = {
        results(wait: true) { $("li.g") }
        result { index -> return results[index] }
        resultLink { index -> result(index).find("h3.r a") }
    }
}

class GoogleSearchSpec extends GebSpec {
    def 'go to google'() {
        when:
        to GoogleHomePage
    	searchField().value "Chuck Norris"
	    searchButton().click()

        then:
	    at GoogleResultsPage
    	resultLink(0).text().contains("Chuck")
    }
}

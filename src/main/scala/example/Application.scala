package example

import org.elasticsearch.client.RestHighLevelClient

object Application extends App {

  val client: RestHighLevelClient = ElasticClient.client
  val elasticSearchService = new ElasticSearchService(client)

  val infected1 = InfectedPersonData(1, "ABC", "DELHI", "High", 110051, "India")
  val infected2 = InfectedPersonData(2, "XYZ", "New York", "Medium", 12018111, "USA")
  val infected3 = InfectedPersonData(3, "Marquez", "Goa", "Low", 1929111, "India")
  val infected4 = InfectedPersonData(4, "Alice", "Mumbai", "High", 1940112, "India")
  val infected5 = InfectedPersonData(5, "Bob", "Delhi", "Medium", 1998111, "India")

  val infectedList = List(infected1, infected2, infected3, infected4, infected5)

  //inserting documents to es
  for (infected <- infectedList) {
    elasticSearchService.insert(infected)
  }

  //searching data
  val data = elasticSearchService.searchByField("id", 3)

  println("searched data " + data)

  ElasticClient.client.close()
}

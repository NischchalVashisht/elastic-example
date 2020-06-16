package example

import example.ElasticSearchService._
import org.elasticsearch.action.delete.{DeleteRequest, DeleteResponse}
import org.elasticsearch.action.index.{IndexRequest, IndexResponse}
import org.elasticsearch.action.search.{SearchRequest, SearchResponse}
import org.elasticsearch.client.{RequestOptions, RestHighLevelClient}
import org.elasticsearch.common.xcontent.{XContentFactory, XContentType}
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.builder.SearchSourceBuilder
import play.api.libs.json.{Format, Json}

object ElasticSearchService {

  val Index = "covidinfected"
  val Type = "patient"
}

class ElasticSearchService(client: RestHighLevelClient) {

  implicit val format: Format[InfectedPersonData] = Json.format[InfectedPersonData]

  def insert(infected: InfectedPersonData): IndexResponse = {
    val indexRequest = new IndexRequest(Index, Type, infected.id.toString)
    val jsonString = Json.stringify(Json.toJson(infected))
    indexRequest.source(jsonString, XContentType.JSON)
    client.index(indexRequest,RequestOptions.DEFAULT)
  }


  def delete(id: String): DeleteResponse = {
    val deleteRequest = new DeleteRequest(Index, Type, id)
    client.delete(deleteRequest,RequestOptions.DEFAULT)
  }

  def searchByField(fieldName: String, value: Any): SearchResponse = {
    val searchRequest = new SearchRequest(Index)
    val searchSourceBuilder = new SearchSourceBuilder

    searchSourceBuilder.query(QueryBuilders.matchPhraseQuery(fieldName, value))
    searchRequest.source(searchSourceBuilder)

    client.search(searchRequest,RequestOptions.DEFAULT)
  }

}

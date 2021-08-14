//package com.gmail.apigeoneer.citywire.utilities
//
//import com.gmail.apigeoneer.citywire.data.models.Article
//import org.json.JSONObject
//
//
///**
// * When there is no value for an Asteroid for the given date, the above fun throws the JsonException,
// * which is caught in our OverviewViewModel. When this exception is thrown,
// * the previous parsed data also will be lost due to the Exception. Hence, we use this approach, wherein we would parse
// * the JSON response as it is like below instead of trying to fetch for the next 7 days or so
// * wherein sometimes there can be no Asteroid for that day and trying to extract one would throw such exceptions
// */
//fun parseArticlesJsonResult(jsonObject: JSONObject): List<Article> {
//    val articleList=mutableListOf<Article>()
//
//    val newsArticlesJson=jsonObject.getJSONObject("news_articles")
//    val dateList=newsArticlesJson.keys()
//    val dateListSorted=dateList.asSequence().sorted()
//    dateListSorted.forEach {
//        val key: String=it
//        val dateArticleJsonArray=newsArticlesJson.getJSONArray(key)
//
//        for (i in 0 until dateArticleJsonArray.length()) {
//            val articleJson=dateArticleJsonArray.getJSONObject(i)
//
//            val title=articleJson.getString("title")
////            val source = articleJson.getSource("source")
//            val author=articleJson.getString("author")
//            val description=articleJson.getString("description")
//            val url=articleJson.getString("url")
//            val urlToImage=articleJson.getString("urlToImage")
//            val publishedAt=articleJson.getString("publishedAt")
//            val content=articleJson.getString("content")
//
//            val article=Article(
//                title,
////            source,
//                author,
//                description,
//                url,
//                urlToImage,
//                publishedAt,
//                content
//            )
//            articleList.add(article)
//        }
//    }
//    return articleList
//}

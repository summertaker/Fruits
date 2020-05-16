package com.summertaker.fruits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val url = "http://summertaker.cafe24.com/reader/akb48_json.php"
        //textView.text = ""

        // Post parameters
        // Form fields and values
        val params = HashMap<String, String>()
        params["foo1"] = "bar1"
        params["foo2"] = "bar2"
        val jsonObject = JSONObject(params as Map<*, *>)

        // Volley post request with parameters
        val request =
                JsonObjectRequest(Request.Method.POST, url, jsonObject, Response.Listener { response ->
                    //println("응답: $response");
                    val fruits = ArrayList<Fruit>()
                    try {
                        val jsonArray = response.getJSONArray("members")
                        val shuffleArray = shuffleJsonArray(jsonArray);
                        if (shuffleArray != null) {
                            for (i in 0 until shuffleArray.length()) {
                                val r = shuffleArray.getJSONObject(i)
                                fruits.add(
                                        Fruit(
                                                r.getString("groups"),
                                                r.getString("team"),
                                                r.getString("name"),
                                                r.getString("furigana"),
                                                r.getString("birthday"),
                                                r.getInt("age"),
                                                r.getString("image")
                                        )
                                )
                            }
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    val adapter = FruitAdapter(fruits)
                    mViewPager.adapter = adapter
                }, Response.ErrorListener {
                    println("에러: $it")
                })

        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                // 0 means no retry
                0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        // Add the volley post request to the request queue
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    /*
    companion object {
        val fruitList = arrayListOf(
                Fruit("Banana", "banana", "A banana is an edible fruit – botanically a berry – produced by several kinds of large herbaceous flowering plants in the genus Musa."),
                Fruit("Strawberry", "strawberry", "The garden strawberry is a widely grown hybrid species of the genus Fragaria, collectively known as the strawberries, which are cultivated worldwide for their fruit."),
                Fruit("Tangerine", "tangerine", "The tangerine is a group of orange-colored citrus fruit consisting of hybrids of mandarin orange."),
                Fruit("Plum", "plum", "A plum is a fruit of the subgenus Prunus of the genus Prunus.")
        )
    }
    */

    /*
     * https://stackoverflow.com/questions/5531130/an-efficient-way-to-shuffle-a-json-array-in-java
     */
    @Throws(JSONException::class)
    fun shuffleJsonArray(array: JSONArray): JSONArray? {
        // Implementing Fisher–Yates shuffle
        val rnd = Random()
        for (i in array.length() - 1 downTo 0) {
            val j: Int = rnd.nextInt(i + 1)
            // Simple swap
            val `object` = array[j]
            array.put(j, array[i])
            array.put(i, `object`)
        }
        return array
    }
}

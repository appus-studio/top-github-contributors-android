package appus.software.githubusers.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import appus.software.githubusers.model.AuthorModel
import appus.software.githubusers.model.ContributorModel
import appus.software.githubusers.net.GitHubClient
import appus.software.githubusers.view.recycler.ClickListener
import appus.software.githubusers.view.recycler.GitHubAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(appus.software.githubusers.R.layout.activity_main)
        supportActionBar?.let {
            it.title =  "GitHubUsers: JetBrains/kotlin"
        }
        recycler.layoutManager = LinearLayoutManager(this)
        showContr()
    }


    fun showContr(){
        loader.visibility = View.VISIBLE

        val call = GitHubClient().getGitHubServiceService().getContributors("JetBrains", "kotlin")
        call.enqueue(object: Callback<List<ContributorModel>> {
            override fun onFailure(call: Call<List<ContributorModel>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                loader.visibility = View.GONE
            }

            override fun onResponse(call: Call<List<ContributorModel>>, response: Response<List<ContributorModel>>) {
                response.body()?.let {
                   val sortedList =  it.reversed().subList(0, 24).sortedBy { it.total }.reversed()
                    recycler.adapter = GitHubAdapter(
                        this@MainActivity,
                        sortedList,
                        object: ClickListener<ContributorModel>{
                        override fun clicked(data: ContributorModel) {
                            goToLocation(data.author.login?: "")
                        }

                    })
                    loader.visibility = View.GONE
                }
            }

        })
    }

    fun goToLocation(login: String){
        loader.visibility = View.VISIBLE
        val call = GitHubClient().getGitHubServiceService().getUserData(login)
        call.enqueue(object: Callback<AuthorModel> {
            override fun onFailure(call: Call<AuthorModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                loader.visibility = View.GONE
            }

            override fun onResponse(call: Call<AuthorModel>, response: Response<AuthorModel>) {
                response.body()?.let {
                    it.location?.let {
                        val address = it.replace(' ', '+').replace(",", "")
                        val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        startActivity(mapIntent)
                    }

                    if (it.location == null || it.location.isEmpty()){
                        Toast.makeText(this@MainActivity, "The user does't have location.", Toast.LENGTH_LONG).show()
                    }
                }
                loader.visibility = View.GONE
            }

        })
    }
}

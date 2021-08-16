package com.gmail.apigeoneer.citywire

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.apigeoneer.citywire.adapters.NewsAdapter
import com.gmail.apigeoneer.citywire.data.models.Article
import com.gmail.apigeoneer.citywire.databinding.FragmentNewsBinding
import com.gmail.apigeoneer.citywire.utilities.NewsBroadcastReceiver
import com.gmail.apigeoneer.citywire.viewmodels.NewsViewModel
import com.gmail.apigeoneer.citywire.viewmodels.NewsViewModelFactory


class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var _adapter: NewsAdapter

    private lateinit var notificationManager: NotificationManager

    private var articleList: List<Article> = listOf(Article("", "", "", "", "", "", ""))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        notificationManager=getSystemService(
            context!!,
            NotificationManager::class.java
        ) as NotificationManager

        val _viewModel: NewsViewModel by viewModels {
            NewsViewModelFactory(requireNotNull(activity?.application))
        }

        linearLayoutManager=LinearLayoutManager(context)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner=this
        binding.newsViewModel=_viewModel

        Log.d(TAG, "::::::: _viewModel.articles.value : $articleList :::::::")

        binding.newsRecyclerView.layoutManager=linearLayoutManager
        binding.newsRecyclerView.setHasFixedSize(true)

        // Observe API's articles response is fetched & set the recycler view
        setupUI(_viewModel)

        // Observe which news item the user clicks on & navigate to its detail screen
        navigateToArticleDetail(_viewModel)

        createChannel(
            getString(R.string.news_notification_channel_id),
            getString(R.string.news_notification_channel_name)
        )

        scheduleNewsNotification()

        return binding.root
    }

    private fun scheduleNewsNotification() {
        // Get the alarm manager
        val alarmManager=activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

        // Create a new intent specifying the broadcast receiver
        val intent=Intent(context, NewsBroadcastReceiver::class.java)

        val pendingIntent=PendingIntent.getBroadcast(context, 0, intent, 0)

        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent)
        }

        // Set the repeating alarm that will be fired every day
        alarmManager?.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME,
            SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
            AlarmManager.INTERVAL_FIFTEEN_MINUTES,
            pendingIntent
        )

//        Toast.makeText(context, "Notifications are scheduled", Toast.LENGTH_SHORT).show()

        //        // Disable a receiver (for example, if the user cancels an alarm)
//        val receiver = ComponentName(context!!, NewsBroadcastReceiver::class.java)
//
//        context.packageManager.setComponentEnabledSetting(receiver,
//            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//            PackageManager.DONT_KILL_APP
//        )
//
    }

    private fun createChannel(channelId: String, channelName: String) {
        // Step 1.6 START create a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel=NotificationChannel(
                channelId,
                channelName,
                // Change importance
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.enableLights(true)
            notificationChannel.lightColor=Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description=
                "Find out what's going on in India. Sports, Entertainment, Business... read it all."
            notificationManager.createNotificationChannel(notificationChannel)

        } else {
            Log.d(TAG, "VERSION.SDK_INT < O")
            Toast.makeText(context, "VERSION.SDK_INT < O", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToArticleDetail(viewModel: NewsViewModel) {
        viewModel.navigateToArticle.observe(viewLifecycleOwner, Observer { article ->
            if (article != null) {
                this.findNavController().navigate(
                    NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(article)
                )
                viewModel.displayArticleDetailsComplete()
            }
        })
    }

    private fun setupUI(viewModel: NewsViewModel) {
        viewModel.repository.articles.observe(viewLifecycleOwner, Observer { articles ->
            if (articles != null) {
                // Set the RecyclerView here
                articleList=viewModel.repository.articles.value!!
                Log.d(TAG, "::::::: _viewModel.articles.value : $articleList :::::::")

                _adapter=NewsAdapter(articleList, NewsAdapter.OnClickListener {
                    viewModel.navigateToDetails(it)
                })
                binding.newsRecyclerView.adapter=_adapter
                _adapter.notifyDataSetChanged()
            }
        })
    }

    companion object {
        private const val TAG="NewsFragment"
    }

}
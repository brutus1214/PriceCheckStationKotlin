package com.example.pricecheckstationkotlin

import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pricecheckstationkotlin.ui.theme.PriceCheckStationKotlinTheme
import com.example.pricecheckstationkotlin.util.GlobalVariable
import com.example.pricecheckstationkotlin.view.ui.PriceCheckScreen

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            PriceCheckStationKotlinTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var decodedData: String? by remember {
                        mutableStateOf("")
                    }
                    var myBroadcastReceiver = object : BroadcastReceiver() {
                        //@Override
                        override fun onReceive(context: Context?, intent: Intent?)  {
                            var action: String? = intent?.getAction()
                            var b: Bundle? = intent?.getExtras()

                            if (action.equals(getResources().getString(com.example.pricecheckstationkotlin.R.string.activity_intent_filter_action))) {
                                //  Received a barcode scan
                                try {
                                    decodedData = intent?.getStringExtra(getResources().getString(com.example.pricecheckstationkotlin.R.string.datawedge_intent_key_data))
                                } catch (e: Exception) {
                                    //  Catch if the UI does not exist when we receive the broadcast
                                }
                            }
                        }
                    }


                    val filter = IntentFilter()
                    filter.addCategory(Intent.CATEGORY_DEFAULT)
                    filter.addAction(getResources().getString(com.example.pricecheckstationkotlin.R.string.activity_intent_filter_action))
                    registerReceiver(myBroadcastReceiver, filter)

                    PriceCheckScreen(decodedData)
                }
            }
        }
    }
}


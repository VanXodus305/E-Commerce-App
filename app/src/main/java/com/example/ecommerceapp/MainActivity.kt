package com.example.ecommerceapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.ecommerceapp.ui.shop.ShopApp
import com.example.ecommerceapp.ui.shop.ShopViewModel
import com.example.ecommerceapp.ui.theme.ECommerceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		Log.d(TAG, "onCreate")
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			val viewModel: ShopViewModel = hiltViewModel()
			val uiState by viewModel.uiState.collectAsStateWithLifecycle()
			ECommerceAppTheme(darkTheme = uiState.darkTheme) {
				ShopApp(viewModel)
			}
		}
	}

	override fun onStart() {
		super.onStart()
		Log.d(TAG, "onStart")
	}

	override fun onResume() {
		super.onResume()
		Log.d(TAG, "onResume")
	}

	override fun onPause() {
		Log.d(TAG, "onPause")
		super.onPause()
	}

	override fun onStop() {
		Log.d(TAG, "onStop")
		super.onStop()
	}

	override fun onDestroy() {
		Log.d(TAG, "onDestroy")
		super.onDestroy()
	}

	companion object {
		private const val TAG = "MainActivity"
	}
}

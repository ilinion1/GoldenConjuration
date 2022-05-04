package com.rockstargames.gta.presentation.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.rockstargames.gta.R
import com.rockstargames.gta.databinding.ActivityWebViewBinding

class WebVIewActivity : AppCompatActivity() {
    lateinit var binding: ActivityWebViewBinding
    private var fileData: ValueCallback<Uri>? = null
    private var filePath: ValueCallback<Array<Uri>>? = null
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private lateinit var link: String
    private var count2 = 0
    private var count1 = 0
    private var count3 = 0
    private var count4 = 0
    private var count5 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getStringExtra("link")?.let { link = it }
        startWebView()
        startResultLauncher()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun startWebView() = with(binding) {
        if (count1 == 0) {
            mainId.loadUrl(link)
            mainId.settings.javaScriptEnabled = true
            mainId.settings.domStorageEnabled = true
            mainId.settings.loadWithOverviewMode = true
            mainId.clearCache(false)
            if (count2 == 0) {
                mainId.settings.cacheMode = WebSettings.LOAD_DEFAULT
                CookieManager.getInstance().setAcceptCookie(true)
                CookieManager.getInstance().setAcceptThirdPartyCookies(mainId, true)
                mainId.webChromeClient = ChromeClient()
                mainId.webViewClient = WebViewClient()
            }
        }

    }


    private inner class ChromeClient : WebChromeClient() {
        override fun onShowFileChooser(
            webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>?,
            fileChooserParams: FileChooserParams?
        ): Boolean {
            if (count2 == 0) {
                filePath = filePathCallback
                launchResult()
            }
            return true
        }
    }


    private fun launchResult() {
        if (count3 == 0) {
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.addCategory(Intent.CATEGORY_OPENABLE)
            i.type = "image/*"
            startForResult.launch(i)
        }
    }


    private fun startResultLauncher() {
        if (count4 == 0) {
            startForResult =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (fileData == null && filePath == null) return@registerForActivityResult
                    val resultFileData: Uri?
                    val resultsFilePath: Array<Uri>?
                    if (count3 == 0) {
                        if (result.data == null) {
                            resultFileData = null
                            resultsFilePath = null
                        } else {
                            resultFileData = result.data?.data
                            resultsFilePath = arrayOf(Uri.parse(result.data?.dataString))
                        }
                        fileData?.onReceiveValue(resultFileData)
                        filePath?.onReceiveValue(resultsFilePath)
                    }
                }
        }
    }

    override fun onBackPressed() {
        if (count5 == 0) {
            if (binding.mainId.canGoBack()) {
                binding.mainId.goBack()
            } else {
                return
            }
        }
    }
}
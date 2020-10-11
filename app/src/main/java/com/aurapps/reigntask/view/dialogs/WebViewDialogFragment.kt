package com.aurapps.reigntask.view.dialogs


import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.aurapps.reigntask.R
import kotlinx.android.synthetic.main.fragment_web_view_dialog.*


class WebViewDialogFragment : DialogFragment() {

    lateinit var url: String
    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        val alertDialog = Dialog(requireContext())
        with(alertDialog) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)

            setContentView(R.layout.fragment_web_view_dialog)
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        return alertDialog
    }

    private fun setListeners(){
        dialog?.run {
            close.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun setWebView(){
        dialog?.run {

            webView.webViewClient = object : WebViewClient() {

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    progress.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progress.visibility = View.GONE
                }

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    return false
                }

                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    return false
                }

            }

            webView.webChromeClient = WebChromeClient()
            webView.settings.builtInZoomControls = true
            webView.settings.displayZoomControls = false
            webView.loadUrl(url)
        }

    }

    override fun onStart() {
        super.onStart()
        setWebView()
        setListeners()
    }

    companion object {
        fun newInstance(url: String) =
            WebViewDialogFragment().apply {
                this.url = url
            }
    }

}

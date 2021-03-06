package com.decagonhq.clads.ui.view.resourcemanagementfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.FragmentResourceViewIndividualArticleBinding

class ResourceViewIndividualArticleFragment : Fragment() {

    private var _binding: FragmentResourceViewIndividualArticleBinding? = null
    private val binding get() = _binding!!
    private val webView: WebView by lazy { binding.resourceViewIndividualArticleFragmentIndividualArticleWebview }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentResourceViewIndividualArticleBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleLink = arguments?.getString(getString(R.string.resource_view_individual_article_fragment_article_link_key))

        webView.webViewClient = object :
            WebViewClient() {}
        if (articleLink != null) {
            webView.loadUrl(articleLink)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                isEnabled = false
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

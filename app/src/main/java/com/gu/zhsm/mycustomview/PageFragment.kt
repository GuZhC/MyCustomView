package com.gu.zhsm.mycustomview

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_page.view.*

class PageFragment : Fragment() {
    @LayoutRes
    internal var mLayoutRes: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_page, container, false)
        view.mStub.layoutResource = mLayoutRes
        view.mStub.inflate()
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            mLayoutRes = args.getInt("myLayoutRes")
        }
    }

    companion object {
        fun newInstance(@LayoutRes layoutRes: Int): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putInt("myLayoutRes", layoutRes)
            fragment.arguments = args
            return fragment
        }
    }
}

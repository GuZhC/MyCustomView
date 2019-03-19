package com.gu.zhsm.mycustomview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val pageModels: MutableList<PageModel> = ArrayList<PageModel>().apply {
        add(PageModel(R.string.cicler_p, R.layout.view_cicler_p))
        add(PageModel(R.string.yibiaopan, R.layout.view_yibiaopan))
        add(PageModel(R.string.cicler_image, R.layout.view_cicler_img))
        add(PageModel(R.string.text_location, R.layout.view_text_location))
        add(PageModel(R.string.text_and_image, R.layout.view_text_img))
        add(PageModel(R.string.material_editText, R.layout.view_material_edittext))
        add(PageModel(R.string.camera_view, R.layout.view_camera_view))
        add(PageModel(R.string.animation_view, R.layout.view_animation_view))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//
//       is AnimationView
//        view.setOnClickListener {
//
//        }
        pager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {

            override fun getItem(position: Int): Fragment {
                val pageModel = pageModels[position]
                return PageFragment.newInstance(pageModel.layoutRes)
            }

            override fun getCount(): Int {
                return pageModels.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return getString(pageModels[position].titleRes)
            }
        }
        tabLayout.setupWithViewPager(pager)

    }


    private inner class PageModel internal constructor(internal var titleRes: Int,
                                                       internal var layoutRes: Int)
}

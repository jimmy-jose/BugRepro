package com.example.bugrepro

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.view_pager_fragment.*


class ViewPagerFrag : Fragment() {

    companion object {
        fun newInstance() = ViewPagerFrag()
    }

    private lateinit var viewModel: ViewPagerViewModel

    private val fragList: ArrayList<Fragment> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragList.add(0, PagerItemFragment.newInstance(1,true))
        fragList.add(1, PagerItemFragment.newInstance(2,false))
        return inflater.inflate(R.layout.view_pager_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pager.adapter =
            ActivityPagerAdapter(
                childFragmentManager,
                context,
                fragList
            )
        tabs.setupWithViewPager(pager)
        viewModel = ViewModelProviders.of(this).get(ViewPagerViewModel::class.java)
    }

    class ActivityPagerAdapter(
        fragmentManager: FragmentManager,
        val context: Context?,
        val list: ArrayList<Fragment>
    ) :
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> list[0]
                1 -> list[1]
                else -> PagerItemFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "ONE"
                1 -> "TWO"
                else -> ""
            }
        }
    }

}

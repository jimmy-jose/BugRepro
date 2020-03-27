package com.example.bugrepro

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bugrepro.di.ViewModelProviderFactory
import com.example.bugrepro.dummy.DummyContent.DummyItem
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_pager_item_list.*
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PagerItemFragment.OnListFragmentInteractionListener] interface.
 */
class PagerItemFragment : DaggerFragment() {

    private var columnCount = 1
    private var oneOrTwo = true

    private var listener: OnListFragmentInteractionListener? = null

    private lateinit var viewModel: PagerItemViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            oneOrTwo = it.getBoolean(ARG_COLOR)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pager_item_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,viewModelFactory).get(oneOrTwo.toString(),PagerItemViewModel::class.java)
        if (oneOrTwo)
            list.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorAccent))
        // Set the adapter
        if (list is RecyclerView) {
            with(list) {
                layoutManager =
//                    when {
//                        columnCount <= 1 ->
                LinearLayoutManager(context)
//                        else -> GridLayoutManager(context, columnCount)
//                    }
                adapter = if (oneOrTwo)
                    MyPagerItemRecyclerViewAdapter(listener)
                else
                    MyPagerItemRecyclerViewAdapter(listener)
            }
        }
        viewModel.refresh(oneOrTwo)
        swipeToRefresh.setOnRefreshListener {
            (list.adapter as MyPagerItemRecyclerViewAdapter).setData(emptyList())
            Handler().postDelayed({
                viewModel.refresh(oneOrTwo)

            },1000)
        }

        viewModel.listItems.observe(viewLifecycleOwner, Observer {
            swipeToRefresh.isRefreshing = false
            (list.adapter as MyPagerItemRecyclerViewAdapter).setData(it)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"
        const val ARG_COLOR = "color"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int, oneOrTwo: Boolean) =
            PagerItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                    putBoolean(ARG_COLOR, oneOrTwo)
                }
            }
    }
}

package com.sournary.materialdesigntutorial.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.Product
import com.sournary.materialdesigntutorial.ui.BaseFragment
import com.sournary.materialdesigntutorial.util.setSupportActionBar

/**
 * Created: 05/09/2018
 * By: Sang
 * Description:
 */
class ProductFragment : BaseFragment() {

    private lateinit var rootView: View

    private lateinit var listScene: Scene
    private lateinit var productDetailScene: Scene
    private lateinit var currentScene: Scene

    private lateinit var transitionManager: TransitionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = ChangeBounds()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_list, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupTransitions()
    }

    private fun setupToolbar() {
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar) { title = "Card" }
    }

    private fun setupProductList() {
        val productAdapter = ProductAdapter(object : ProductUserActionsListener {

            override fun onItemClick(view: View, product: Product, position: Int) {
//
//                transitionManager.setTransition(
//                    listScene,
//                    productDetailScene,
//                    ChangeTransform().apply {
//                        duration = 10000
//                        addTarget(R.id.recycler_product)
//                    })
//                transitionManager.transitionTo(productDetailScene)
//                currentScene = productDetailScene
                NavHostFragment.findNavController(this@ProductFragment).navigate(R.id.action_list_to_detail)
            }
        })
        productAdapter.submitList(createProductList())
        val productRecycler = rootView.findViewById<RecyclerView>(R.id.recycler_product)
        productRecycler.apply {
            adapter = productAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupTransitions() {
        transitionManager = TransitionManager()
        val transitionView = rootView.findViewById<ViewGroup>(R.id.root_product_list)
        productDetailScene =
                Scene.getSceneForLayout(transitionView, R.layout.fragment_product_detail, activity!!)
        listScene =
                Scene.getSceneForLayout(transitionView, R.layout.fragment_list, activity!!)
        listScene.setEnterAction {
            setupToolbar()
            setupProductList()
        }
        currentScene = listScene
        val changeBounds = ChangeBounds().apply {
            duration = 10000
            addTarget(R.id.root_item_product)
        }
        transitionManager.setTransition(productDetailScene, listScene, changeBounds)
        listScene.enter()
    }

    override fun onBackPress() {
//        if (currentScene == productDetailScene) {
//            transitionManager.transitionTo(listScene)
//            return
//        }
        NavHostFragment.findNavController(this).popBackStack()
    }

    private fun createProductList(): List<Product> {
        val generalOverview = resources.getString(R.string.paragraph_random_text)

        val pencil = Product(
            imageUrl = R.drawable.img_avatar_10_raster,
            name = "Pencil",
            location = "In stock",
            money = "$1.50",
            overview = generalOverview,
            focus = "HB hardware only"
        )

        val rubberbands = Product(
            imageUrl = R.drawable.img_cafebook_background,
            name = "Rubberbands",
            location = "In stock",
            money = "$4.50",
            overview = generalOverview,
            focus = "Computer only"
        )

        return listOf(pencil, rubberbands)
    }
}

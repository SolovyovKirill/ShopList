package com.shoppinglist.presentation.screen.shopitemfragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.shoppinglist.R
import com.shoppinglist.domain.shopitem.ShopItem
import com.shoppinglist.presentation.viewmodel.shopitemviewmodel.ShopItemViewModel

class ShopItemFragment(
    //Incorrect passing of parameters to the fragment
    private val screenMode: String = MODE_UNKNOWN,
    private val shopItemId: Int = ShopItem.UNDEFINED_ID
) : Fragment() {

    private lateinit var shopItemViewModel: ShopItemViewModel

    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var btnSave: MaterialButton


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parseParams()
        shopItemViewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews(view)
        addTextChangeListener()
        lunchRightMode()
        observeViewModel()
    }


    private fun observeViewModel() {
        shopItemViewModel.errorInputNameLiveData.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            tilCount.error = message
        }
        shopItemViewModel.errorInputCountLiveData.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            tilName.error = message
        }
        shopItemViewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }
    }

    private fun lunchRightMode() {
        when (screenMode) {
            MODE_EDIT -> lunchEditMode()
            MODE_ADD -> lunchAddMode()
        }
    }

    private fun addTextChangeListener() {
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                shopItemViewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                shopItemViewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    private fun lunchEditMode() {
        shopItemViewModel.getShopItemId(shopItemId)
        shopItemViewModel.shopItemLiveData.observe(viewLifecycleOwner) {
            etName.setText(it.name)
            etCount.setText(it.count.toString())
        }
        btnSave.setOnClickListener {
            shopItemViewModel.editShopItem(etName.text?.toString(), etCount.text?.toString())
        }
    }

    private fun lunchAddMode() {
        btnSave.setOnClickListener {
            shopItemViewModel.addShopItem(etName.text?.toString(), etCount.text?.toString())
        }
    }

    private fun parseParams() {
        if (screenMode != MODE_EDIT && screenMode != MODE_ADD) {
            throw RuntimeException("Param screen mode is absent ${screenMode}")
        }

        if (screenMode == MODE_EDIT && shopItemId == ShopItem.UNDEFINED_ID) {
            throw RuntimeException("Param shop item id is absent")
        }
    }

    private fun initViews(view: View) {
        tilName = view.findViewById(R.id.til_name)
        tilCount =  view.findViewById(R.id.til_count)
        etName =  view.findViewById(R.id.et_name)
        etCount =  view.findViewById(R.id.et_count)
        btnSave =  view.findViewById(R.id.btn_save)
    }

    companion object {
        const val SCREEN_MODE = "extra_mode"
        const val SHOP_ITEM_ID = "extra_shop_item_id"
        const val MODE_EDIT = "mode_edit"
        const val MODE_ADD = "mode_add"
        const val MODE_UNKNOWN = ""

        fun newInstanceAddItem() : ShopItemFragment =
            ShopItemFragment(MODE_ADD)

        fun newInstanceEditItem(shopItemId: Int) : ShopItemFragment =
            ShopItemFragment(MODE_EDIT, shopItemId)

    }
}
package kztproject.jp.splacounter.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import kztproject.jp.splacounter.MyApplication
import kztproject.jp.splacounter.databinding.FragmentRewardAddBinding
import kztproject.jp.splacounter.viewmodel.RewardAddViewModel
import kztproject.jp.splacounter.viewmodel.RewardAddViewModelCallback
import javax.inject.Inject

class RewardAddFragment : Fragment(), RewardAddViewModelCallback {
    private lateinit var binding: FragmentRewardAddBinding

    @Inject
    lateinit var viewModel: RewardAddViewModel

    companion object {

        fun newInstance(): RewardAddFragment {
            return RewardAddFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity.application as MyApplication).component().inject(this)
        viewModel.setCallback(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRewardAddBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTextListener(binding.name, { s -> viewModel.setName(s) })
        setTextListener(binding.description, { s -> viewModel.setDescription(s) })
        setTextListener(binding.consumePoint, { s -> viewModel.setPoint(s) })
    }

    private fun setTextListener(editText: EditText?, code: (s: String) -> Unit) {
        editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //No Action
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                code(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                //No Action
            }

        })
    }

    override fun onSaveCompleted(rewardName: String) {
        Toast.makeText(context, "Added $rewardName", Toast.LENGTH_SHORT).show()
        fragmentManager.popBackStack()
    }

    override fun onError(resourceId: Int) {
        Toast.makeText(context, resourceId, Toast.LENGTH_SHORT).show()
    }
}
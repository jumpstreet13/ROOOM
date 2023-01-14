package com.abocha.room

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.abocha.room.data.NoteEntity
import com.abocha.room.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    var sizeInDP = 8

    private var _binding: FragmentFirstBinding? = null
    private val viewModel by activityViewModels<FirstFragmentViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val marginInDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, sizeInDP.toFloat(), resources
                .displayMetrics
        ).toInt()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.flow.collect { noteEntities ->
                    binding.itemsContainer.removeAllViews()
                    noteEntities.filterNotNull().forEach {
                        val currentView = createEntityView(it)
                        binding.itemsContainer.addView(currentView)
                        setMargins(currentView, marginInDp, marginInDp, marginInDp, marginInDp)
                    }
                }
            }
        }
        viewModel.requestData(requireContext().applicationContext)
    }

    private fun createEntityView(noteEntity: NoteEntity): View {
        val view = layoutInflater.inflate(R.layout.note_item, null, false)
        view.findViewById<TextView>(R.id.note).text = noteEntity.noteText
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is MarginLayoutParams) {
            val p = view.layoutParams as MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }
}
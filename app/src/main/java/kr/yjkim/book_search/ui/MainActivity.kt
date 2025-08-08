package kr.yjkim.book_search.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kr.yjkim.book_search.R
import kr.yjkim.book_search.data.MyRepository
import kr.yjkim.book_search.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels {
        MyViewModel.create(MyRepository())
    }

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // title text auto-setting disable

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home -> toolbar.visibility = View.GONE
                R.id.info, R.id.list -> toolbar.visibility = View.VISIBLE
            }
        }

        // back button dispatcher
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentDestination = navController.currentDestination?.id
                when {
                    currentDestination == R.id.home -> MaterialAlertDialogBuilder(this@MainActivity)
                        .setMessage(getString(R.string.dialog_message_exit))
                        .setPositiveButton(getString(R.string.dialog_btn_yes_exit)) { dialog, _ ->
                            finish()
                            dialog.dismiss()
                        }
                        .setNeutralButton(getString(R.string.dialog_btn_no)) { dialog, _ ->
                            dialog.cancel()
                        }
                        .setCancelable(false)
                        .show()

                    else -> navController.navigateUp()
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }

    fun setToolbar(title: String, showBackButton: Boolean) {
        toolbar.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
    }
}
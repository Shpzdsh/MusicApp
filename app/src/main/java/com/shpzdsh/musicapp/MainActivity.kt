package com.shpzdsh.musicapp

import android.app.Dialog
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.saadahmedsoft.popupdialog.PopupDialog
import com.saadahmedsoft.popupdialog.Styles.ANDROID_DEFAULT
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener
import com.shpzdsh.musicapp.core.ActivityStateProcessor

class MainActivity : AppCompatActivity(), ActivityStateProcessor {

    private val progressBar by lazy(LazyThreadSafetyMode.NONE) { findViewById<ProgressBar>(R.id.progressBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun processLoading(state: Boolean) {
        progressBar.isVisible = state
    }

    override fun processError(message: String, onReloadPage: () -> Unit) {
        showPopup(
            header = resources.getString(R.string.error_header),
            description = resources.getString(R.string.error_description).format(message)
        ) {
            onReloadPage()
        }
    }

    private fun showPopup(
        header: String,
        description: String,
        onPositivePage: () -> Unit
    ) {
        PopupDialog.getInstance(this)
            .setStyle(ANDROID_DEFAULT)
            .setDescription(description)
            .setNegativeButtonText(resources.getString(R.string.negative_popup))
            .setPositiveButtonText(resources.getString(R.string.positive_popup))
            .setHeading(header)
            .setCancelable(false)
            .showDialog(
                object : OnDialogButtonClickListener() {
                    override fun onPositiveClicked(dialog: Dialog?) {
                        super.onPositiveClicked(dialog)
                        onPositivePage()
                    }

                    override fun onNegativeClicked(dialog: Dialog?) {
                        super.onNegativeClicked(dialog)
                        finish()
                    }
                }
            )
    }
}
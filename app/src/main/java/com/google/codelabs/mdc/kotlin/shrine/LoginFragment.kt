package com.google.codelabs.mdc.kotlin.shrine

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.shr_login_fragment.view.*


/**
 * Fragment representing the login screen for Shrine.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.shr_login_fragment, container, false)
        view.donate.movementMethod = LinkMovementMethod.getInstance()
        // Set an error if the password is less than 8 characters.
        view.next_button.setOnClickListener {
            (activity as NavigationHost).navigateTo(ProductGridFragment(), false) // Navigate to the next Fragment
        }
        view.contact_button.setOnClickListener {
            fireContactUI()
        }
        return view
    }

    private fun fireContactUI() {
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "bacloud14@gmail.com", null))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, context?.getString(R.string.label_subject_email))
        emailIntent.putExtra(Intent.EXTRA_TEXT, context?.getString(R.string.label_email_description))
        startActivity(emailIntent)
    }

    /*
        In reality, this will have more complex logic including, but not limited to, actual
        authentication of the username and password.
     */
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}

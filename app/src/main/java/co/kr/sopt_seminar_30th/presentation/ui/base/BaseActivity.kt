package co.kr.sopt_seminar_30th.presentation.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import timber.log.Timber

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: T
    abstract val TAG: String
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag(TAG).i("onCreate")
        binding = DataBindingUtil.setContentView(this, layoutRes)
    }

    override fun onStart() {
        super.onStart()
        Timber.tag(TAG).i("onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.tag(TAG).i("onRestart")
    }

    override fun onResume() {
        super.onResume()
        Timber.tag(TAG).i("onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.tag(TAG).i("onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.tag(TAG).i("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag(TAG).i("onDestroy")
    }
}
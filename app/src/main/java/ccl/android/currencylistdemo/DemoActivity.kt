package ccl.android.currencylistdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import ccl.android.currencylistdemo.databinding.ActivityDemoBinding

class DemoActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(this)
        viewBinding = ActivityDemoBinding.inflate(inflater)
        setContentView(viewBinding.root)
    }
}

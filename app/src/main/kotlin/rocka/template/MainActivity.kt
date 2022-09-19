package rocka.template

import android.app.Activity
import android.os.Bundle
import rocka.template.databinding.ActivityMainBinding

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

package ru.startandroid.develop.simpleadaptercustom2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.SimpleAdapter

const val ATTRIBUTE_NAME_TEXT = "text"
const val ATTRIBUTE_NAME_PB = "pb"
const val ATTRIBUTE_NAME_LL = "ll"

class MainActivity : AppCompatActivity() {

    var lvSimple: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val load = intArrayOf(41, 48, 22, 35, 30, 67, 51, 88)

        val data =ArrayList<Map<String, Any?>>(load.size)
        var m:MutableMap<String, Any?>
        for (i in load.indices) {
            m = HashMap()
            m.put(ATTRIBUTE_NAME_TEXT, "Day ${i + 1}. Load: ${load[i]}%")
            m.put(ATTRIBUTE_NAME_PB, load[i])
            m.put(ATTRIBUTE_NAME_LL, load[i])
            data.add(m)
        }
        val from = arrayOf(ATTRIBUTE_NAME_TEXT,
            ATTRIBUTE_NAME_PB,
            ATTRIBUTE_NAME_LL)

        val to = intArrayOf(R.id.tvLoad, R.id.pbLoad, R.id.llLoad)

        val sAdapter = SimpleAdapter(this,
            data,
            R.layout.item,
            from,
            to)

        sAdapter.viewBinder = MyViewBinder()

        lvSimple = findViewById(R.id.lvSimple)
        lvSimple!!.adapter = sAdapter
    }

    internal inner class MyViewBinder : SimpleAdapter.ViewBinder {
        var red = resources.getColor(R.color.Red)
        var orange = resources.getColor(R.color.Orange)
        var green = resources.getColor(R.color.Green)

        override fun setViewValue(view: View?, data: Any?, textRepresentation: String?): Boolean {
            var i = 0
            when(view!!.id) {
                R.id.llLoad -> {
                    i = data as Int
                    if (i < 40) {
                        view.setBackgroundColor(green)
                        } else if (i < 70) {
                            view.setBackgroundColor(orange)
                        } else {
                            view.setBackgroundColor(red)
                        }
                    return true
                    }
                R.id.pbLoad -> {
                    i = data as Int
                    (view as ProgressBar).progress = i
                    return true
                }
            }
            return false
        }
    }
}

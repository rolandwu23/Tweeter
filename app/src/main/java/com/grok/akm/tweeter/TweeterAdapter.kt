package com.grok.akm.tweeter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TweeterAdapter(var con: Context, var strings: List<String>) : ArrayAdapter<String>(con, 0, strings) {


    override fun getView(position: Int, convertview: View?, parent: ViewGroup): View {
        var convertView = convertview

        val viewholder: ViewHolder
        if (convertView == null)
        {
            convertView = LayoutInflater.from(con).inflate(R.layout.card_view, parent, false)
            viewholder = ViewHolder(convertView!!)
            convertView.tag = viewholder
        } else {
            viewholder = convertView.tag as ViewHolder
        }

        val size = strings.size.toString()
        val st = getItem(position)

        if(strings.size == 1)
        {
            viewholder.infoText.text = st
        }
        else {
            val text = (position + 1).toString() + "/" + size + " " + st
            viewholder.infoText.text = text
            Log.e("text", text)
        }

        return convertView
    }

//    use  ViewHolder to support recycling of views

    inner class ViewHolder internal constructor(row: View) {
        internal var infoText: TextView = row.findViewById<View>(R.id.card_view_ans_text) as TextView

    }
}
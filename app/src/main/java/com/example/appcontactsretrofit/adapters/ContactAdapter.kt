package com.example.appcontactsretrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.appcontactsretrofit.R
import com.example.appcontactsretrofit.databinding.ContactItemBinding
import com.example.appcontactsretrofit.retrofit.Item
import com.example.appcontactsretrofit.retrofit.ResultItem

class ContactAdapter(private val contactList :List<ResultItem>):RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    class ContactHolder( val binding: ContactItemBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContactHolder(binding)
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val currentItem = contactList[position]
        with(holder.binding){
            fullName.text= "${currentItem.name.title} ${currentItem.name.first} ${currentItem.name.last}"

            Glide.with(holder.binding.root)
                .load(currentItem.picture.large)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageContact)


            emailContact.text=currentItem.email
            phoneContact.text=currentItem.phone
            contactMail.text=currentItem.gender
            if (currentItem.gender=="male"){
               imageViewFemale.setImageResource(R.drawable.ic_male)
            }else{
                imageViewFemale.setImageResource(R.drawable.ic_female)
            }

        }

    }
}

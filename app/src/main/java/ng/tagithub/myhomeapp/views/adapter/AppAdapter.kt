package ng.tagithub.myhomeapp.views.adapter

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ng.tagithub.myhomeapp.R

class AppAdapter(
    private var apps: List<ResolveInfo>,
    private val packageManager: PackageManager,
    private val onAppClick: (ResolveInfo) -> Unit
) : RecyclerView.Adapter<AppAdapter.AppViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.app_item_layout, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = apps[position]
        holder.name.text = app.loadLabel(packageManager)
        holder.icon.setImageDrawable(app.loadIcon(packageManager))
        holder.itemView.setOnClickListener { onAppClick(app) }
    }

    override fun getItemCount(): Int = apps.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newApps: List<ResolveInfo>) {
        apps = newApps
        notifyDataSetChanged()
    }

    class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.appIcon)
        val name: TextView = view.findViewById(R.id.appName)
    }
}
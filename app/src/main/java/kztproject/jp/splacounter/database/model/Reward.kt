package kztproject.jp.splacounter.database.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
data class Reward(@PrimaryKey(autoGenerate = true) val id: Int,
                  val name: String,
                  val consumePoint: Int,
                  val description: String?,
                  val needRepeat: Boolean,
                  @set:Ignore var isSelected: Boolean = false)
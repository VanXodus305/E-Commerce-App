package com.example.ecommerceapp.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.ecommerceapp.data.model.UserPreferences
import com.example.ecommerceapp.data.model.SortMode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.userDataStore by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferencesRepository @Inject constructor(
	@ApplicationContext private val context: Context
) {
	private val darkThemeKey = booleanPreferencesKey("dark_theme")
	private val selectedCategoryKey = stringPreferencesKey("selected_category")
	private val selectedSortModeKey = stringPreferencesKey("selected_sort_mode")
	private val lastSyncKey = longPreferencesKey("last_sync")

	val preferencesFlow: Flow<UserPreferences> = context.userDataStore.data
		.catch { exception ->
			if (exception is IOException) {
				emit(androidx.datastore.preferences.core.emptyPreferences())
			} else {
				throw exception
			}
		}
		.map { prefs ->
			UserPreferences(
				darkTheme = prefs[darkThemeKey] ?: false,
				selectedCategory = prefs[selectedCategoryKey] ?: "All",
				selectedSortMode = prefs[selectedSortModeKey] ?: SortMode.RatingHighToLow.name,
				lastSyncAt = prefs[lastSyncKey] ?: 0L
			)
		}

	suspend fun setDarkTheme(enabled: Boolean) {
		context.userDataStore.edit { prefs ->
			prefs[darkThemeKey] = enabled
		}
	}

	suspend fun setSelectedCategory(category: String) {
		context.userDataStore.edit { prefs ->
			prefs[selectedCategoryKey] = category
		}
	}

	suspend fun setSelectedSortMode(mode: SortMode) {
		context.userDataStore.edit { prefs ->
			prefs[selectedSortModeKey] = mode.name
		}
	}

	suspend fun setLastSync(timestamp: Long) {
		context.userDataStore.edit { prefs ->
			prefs[lastSyncKey] = timestamp
		}
	}
}

package com.wahyurhy.androidtvleanback

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.leanback.widget.BrowseFrameLayout
import com.wahyurhy.androidtvleanback.fragment.*
import com.wahyurhy.androidtvleanback.utils.Common
import com.wahyurhy.androidtvleanback.utils.Constants

class MainActivity : FragmentActivity(), View.OnKeyListener {

    private lateinit var fragmentContainer: FrameLayout
    private lateinit var navBar: BrowseFrameLayout

    private lateinit var btnSearch: TextView
    private lateinit var btnHome: TextView
    private lateinit var btnTvShow: TextView
    private lateinit var btnMovie: TextView
    private lateinit var btnSport: TextView
    private lateinit var btnSettings: TextView
    private lateinit var btnLanguage: TextView
    private lateinit var btnGenre: TextView

    private var SIDE_MENU = false
    private var selectedMenu = Constants.MENU_HOME

    private lateinit var lastSelectedMenu: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        changeFragment(HomeFragment())
    }

    private fun initView() {
        fragmentContainer = findViewById(R.id.container)
        navBar = findViewById(R.id.bflNavBar)
        btnSearch = findViewById(R.id.btn_search)
        btnHome = findViewById(R.id.btn_home)
        btnTvShow = findViewById(R.id.btn_tv)
        btnMovie = findViewById(R.id.btn_movies)
        btnSport = findViewById(R.id.btn_sports)
        btnSettings = findViewById(R.id.btn_settings)
        btnLanguage = findViewById(R.id.btn_language)
        btnGenre = findViewById(R.id.btn_genre)

        initSetOnKey()
    }

    private fun initSetOnKey() {
        btnSearch.setOnKeyListener(this)
        btnHome.setOnKeyListener(this)
        btnTvShow.setOnKeyListener(this)
        btnMovie.setOnKeyListener(this)
        btnSport.setOnKeyListener(this)
        btnSettings.setOnKeyListener(this)
        btnLanguage.setOnKeyListener(this)
        btnGenre.setOnKeyListener(this)

        lastSelectedMenu = btnHome
        lastSelectedMenu.isActivated = true
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()

        closeMenu()
    }

    override fun onKey(view: View?, i: Int, keyEvent: KeyEvent?): Boolean {
        when (i) {
            KeyEvent.KEYCODE_DPAD_CENTER -> {

                lastSelectedMenu.isActivated = false
                view?.isActivated = true
                lastSelectedMenu = view!!

                when (view.id) {
                    R.id.btn_search -> {
                        selectedMenu = Constants.MENU_SEARCH
                        changeFragment(SearchFragment())
                    }
                    R.id.btn_home -> {
                        selectedMenu = Constants.MENU_HOME
                        changeFragment(HomeFragment())
                    }
                    R.id.btn_tv -> {
                        selectedMenu = Constants.MENU_TV
                        changeFragment(TvShowFragment())
                    }
                    R.id.btn_movies -> {
                        selectedMenu = Constants.MENU_MOVIE
                        changeFragment(MovieFragment())
                    }
                    R.id.btn_sports -> {
                        selectedMenu = Constants.MENU_SPORTS
                        changeFragment(SportsFragment())
                    }
                    R.id.btn_settings -> {
                        selectedMenu = Constants.MENU_SETTINGS
                        changeFragment(SettingsFragment())
                    }
                    R.id.btn_language -> {
                        selectedMenu = Constants.MENU_LANGUAGE
                        changeFragment(LanguageFragment())
                    }
                    R.id.btn_genre -> {
                        selectedMenu = Constants.MENU_GENRES
                        changeFragment(GenresFragment())
                    }
                }
            }

            KeyEvent.KEYCODE_DPAD_LEFT -> {
                if (!SIDE_MENU) {
                    switchToLastSelectedMenu()
                    openMenu()
                    SIDE_MENU = true
                }
            }
        }
        return false
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && SIDE_MENU) {
            SIDE_MENU = false
            closeMenu()
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        if (SIDE_MENU) {
            SIDE_MENU = false
            closeMenu()
        } else {
            super.onBackPressed()
        }
    }

    private fun switchToLastSelectedMenu() {
        when (selectedMenu) {
            Constants.MENU_SEARCH -> {
                btnSearch.requestFocus()
            }
            Constants.MENU_HOME -> {
                btnHome.requestFocus()
            }
            Constants.MENU_TV -> {
                btnTvShow.requestFocus()
            }
            Constants.MENU_SPORTS -> {
                btnSport.requestFocus()
            }
            Constants.MENU_LANGUAGE -> {
                btnLanguage.requestFocus()
            }
            Constants.MENU_GENRES -> {
                btnGenre.requestFocus()
            }
            Constants.MENU_SETTINGS -> {
                btnSettings.requestFocus()
            }
        }
    }

    private fun openMenu() {
        val animSlide: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        navBar.startAnimation(animSlide)
        navbarSize(16)
    }

    private fun closeMenu() {
        navbarSize(5)

        fragmentContainer.requestFocus()
        SIDE_MENU = false
    }

    private fun navbarSize(size: Int) {
        navBar.requestLayout()
        navBar.layoutParams.width = Common.getWidthInPercent(this, size)
    }
}
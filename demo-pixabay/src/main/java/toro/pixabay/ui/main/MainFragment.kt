/*
 * Copyright (c) 2018 Nam Nguyen, nam@ene.im
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package toro.pixabay.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import im.ene.toro.widget.Container
import toro.pixabay.Injectable
import toro.pixabay.R
import toro.pixabay.di.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment(), Injectable {

  companion object {
    fun newInstance() = MainFragment()
  }

  @Inject
  lateinit var factory: ViewModelFactory
  private lateinit var viewModel: MainViewModel

  var container: Container? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.main_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    container = view.findViewById(R.id.container)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = factory.create(MainViewModel::class.java)
    val adapter = MainAdapter()
    viewModel.searchPhotos("Universe").items.observe(this, Observer { adapter.submitList(it) })

    container!!.adapter = adapter
    container!!.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
  }

}
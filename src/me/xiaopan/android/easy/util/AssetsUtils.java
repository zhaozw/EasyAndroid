/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xiaopan.android.easy.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import me.xiaopan.java.easy.io.IOUtils;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Assets文件操作工具箱
 */
public class AssetsUtils {
	
	/**
	 * 读取给定文件名的文件的内容并转换成字符串
	 * @param context 上下文
	 * @param fileName 文件名
	 * @param charset 转换编码
	 * @return
	 */
	public static final String getString(Context context, String fileName, Charset charset){
		InputStream inputStream = null;
		try {
			inputStream = context.getAssets().open(fileName);
			byte[] bytes = IOUtils.read(inputStream);
			inputStream.close();
			return new String(bytes, 0, bytes.length, charset.name());
		} catch (IOException e) {
			e.printStackTrace();
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		}
	}
	
	/**
	 * 读取给定文件名的文件的内容并转换成字符串
	 * @param context 上下文
	 * @param fileName 文件名
	 * @return
	 */
	public static final String getString(Context context, String fileName){
		return getString(context, fileName, Charset.defaultCharset());
	}
	
	/**
	 * 获取位图
	 * @param context 上下文
	 * @param fileName 文件名称
	 * @param outPadding 输出位图的内边距
	 * @param options 加载选项
	 * @return
	 */
	public static final Bitmap getBitmap(Context context, String fileName, Rect outPadding, BitmapFactory.Options options){
		return BitmapDecoder.getInstance().decodeFromAssets(context, fileName, outPadding, options);
	}
	
	/**
	 * 获取位图
	 * @param context 上下文
	 * @param fileName 文件名称
	 * @return
	 */
	public static final Bitmap getBitmap(Context context, String fileName){
		return BitmapDecoder.getInstance().decodeFromAssets(context, fileName);
	}
}
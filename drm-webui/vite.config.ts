import path from 'path'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from "unplugin-vue-components/resolvers";

const pathSrc = path.resolve(__dirname, 'src')
// https://vitejs.dev/config/
export default defineConfig({
    resolve: {
        alias: {
            '~/': `${pathSrc}/`,
        },
    },
    plugins: [
      //Vue相关插件
      vue(),
      //unplugin-auto-import
      //自动import相关的API函数
      AutoImport({
          //预设引入
          imports: [
          'vue',
          'vue-router',
          ],
          resolvers:[
              ElementPlusResolver()
          ],
          dts:'src/auto-imports.d.ts',
      }),
      //unplugin-vue-components
      //自动import Vue组件
      Components({
          include: [/\.vue$/, /\.vue\?vue/],
          resolvers:[
              ElementPlusResolver(),
          ],
          dts:'src/components.d.ts',
      }),
    ],
})

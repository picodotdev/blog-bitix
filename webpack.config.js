var webpack = require('webpack');
var path = require('path');

module.exports = {
	entry: {
		'vendor': ['jquery'],
		'app-theme': './themes/bitix/static/assets/js/app-theme.js',
		'app': './static/assets/js/app.js'
	},
    output: {
        path: path.resolve(__dirname, 'static/assets/js/bundle/'),
        filename: '[name].bundle.js'
    },
    resolve: {
		extensions: ['.js'],
		alias: {
			'jquery': path.resolve(__dirname, 'themes/bitix/static/assets/libs/jquery-3.2.1.slim.min.js'),
			'popper': path.resolve(__dirname, 'themes/bitix/static/assets/libs/popper.js-1.12.3/popper.min.js'),
			'bootstrap': path.resolve(__dirname, 'themes/bitix/static/assets/libs/bootstrap-4.0.0-beta/js/bootstrap.min.js'),
			'blueimp-helper': path.resolve(__dirname, 'themes/bitix/static/assets/libs/Gallery-2.25.2/js/blueimp-helper.js'),
			'blueimp-gallery': path.resolve(__dirname, 'themes/bitix/static/assets/libs/Gallery-2.25.2/js/blueimp-gallery.min.js'),
			'./blueimp-gallery': path.resolve(__dirname, 'themes/bitix/static/assets/libs/Gallery-2.25.2/js/blueimp-gallery.min.js'),
			'blueimp-gallery-indicator': path.resolve(__dirname, 'themes/bitix/static/assets/libs/Gallery-2.25.2/js/blueimp-gallery-indicator.js'),
			'blueimp-gallery-video': path.resolve(__dirname, 'themes/bitix/static/assets/libs/Gallery-2.25.2/js/blueimp-gallery-video.js'),
			'blueimp-gallery-vimeo': path.resolve(__dirname, 'themes/bitix/static/assets/libs/Gallery-2.25.2/js/blueimp-gallery-vimeo.js'),
			'blueimp-gallery-youtube': path.resolve(__dirname, 'themes/bitix/static/assets/libs/Gallery-2.25.2/js/blueimp-gallery-youtube.js'),
			'jquery-blueimp-gallery': path.resolve(__dirname, 'themes/bitix/static/assets/libs/Gallery-2.25.2/js/jquery.blueimp-gallery.min.js')
		}
    },
    module: {
		rules: [
			{
				test: /\.js$/,
				exclude: /(node_modules|bower_components)/,
				use: {
					loader: 'babel-loader',
					options: {
						presets: ['env']
					}
				}
			}
		]
	},
	plugins: [
		new webpack.ProvidePlugin({
		  $: 'jquery',
		  jQuery: 'jquery',
		  Popper: 'popper'
		}),
		new webpack.optimize.CommonsChunkPlugin({
			name: 'vendor'
		})
	]
}

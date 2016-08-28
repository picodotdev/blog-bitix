module.exports = function(grunt) {
    grunt.initConfig({
		htmlmin: {
            dev: {
                options: {
                    removeComments: true,
                    collapseWhitespace: true
                },
                files: [{
                    expand: true,
                    cwd: 'public/',
                    src: '**/*.html',
                    dest: 'public/'
                }]
            },
            prod: {
                options: {
                    removeComments: true,
                    collapseWhitespace: true
                },
                files: [{
                    expand: true,
                    cwd: 'deploy/',
                    src: '**/*.html',
                    dest: 'deploy/'
                }]
            }
        },
        less: {
            dev: {
                options: {
                    compress: true,
                    yuicompress: true
                },
                files: {
		          "themes/bitix/static/assets/css/main.css": "themes/bitix/static/assets/css/main.less"
                }
            }
        },
        watch: {
            files: ['themes/bitix/static/assets/css/*.less'],
            tasks: ["less"]
        }
    });
	grunt.loadNpmTasks('grunt-contrib-htmlmin');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.registerTask('default', ['htmlmin', 'less', 'watch']);
};

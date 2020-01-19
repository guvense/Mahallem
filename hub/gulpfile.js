const gulp = require("gulp");
const ts = require("gulp-typescript");
const tslint = require('gulp-tslint');
const sourcemaps = require('gulp-sourcemaps');
const tsProject = ts.createProject("./tsconfig.json");

gulp.task("tslint", () =>
    gulp.src('./src/**/*.ts', { base: '.' })
        .pipe(tslint({
            formatter: "verbose"
        }))
        .pipe(tslint.report())
);

gulp.task("build", function () {
    return tsProject.src()
        .pipe(sourcemaps.init())
        .pipe(tsProject())
        .js.pipe(sourcemaps.write("./", { sourceRoot: __dirname })).pipe(gulp.dest("./dist"));
});

gulp.task('default', ['tslint', 'build']);
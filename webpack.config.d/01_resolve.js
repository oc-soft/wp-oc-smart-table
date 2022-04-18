

/**
 * manage resolve for webpack
 */
class Resolve {

  /**
   * setup configuration
   */
  setup(config) {
    const resolve = config.resolve || {}
    const modules = resolve.modules || {}
    const entryMap = GradleBuild.config.js[config.mode]

    const path = require('path');
    for (const key in entryMap) {
      modules.unshift(path.dirname(entryMap[key]))
    }
    resolve.modules = modules
    config.resolve = resolve
  }
}


((config)=> {
  const resolve = new Resolve()
  resolve.setup(config)
})(config)

// vi: se ts=2 sw=2 et:

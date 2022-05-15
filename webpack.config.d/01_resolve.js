

/**
 * manage resolve for webpack
 */
class Resolve {

  /**
   * setup configuration
   */
  setup(config) {
    this.setupAlias(config)
  }

  /**
   * setup alias
   */
  setupAlias(config) {
    const resolve = config.resolve || {}
    const alias = resolve.alias || {}
 

    alias.kotlin = './kotlin.js'
    
    resolve.alias = alias
    config.resolve = resolve
  }


  /**
   * setup configuration
   */
  setupConfig(config) {
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

import * as config from 'config'
import mahallem from './app'

console.log(`
    Mahallem Hub Server\n
    NODE_ENV: ${config.util.getEnv('NODE_ENV')}
    NODE_CONFIG_DIR: ${config.util.getEnv('NODE_CONFIG_DIR')}
    NODE_CONFIG: ${config.util.getEnv('NODE_CONFIG')}\n
    NODE_VERSION`, process.version)
mahallem.bootstrap()

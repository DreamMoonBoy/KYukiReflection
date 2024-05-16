import { i18n } from './utils';

const baseApiPath = '/api/public/com/highcapable/yukireflection/';

const baseKotlinApiPath = '/api/public/com/dream/yukireflection/';

const navigationLinks = {
    start: [
        '/guide/home',
        '/guide/quick-start'
    ],
    config: [
        '/config/api-example',
        '/config/api-exception'
    ],
    apiDocs: [
        '/api/home',
        '/api/public/',
        '/api/features'
    ],
    publicApi: [
        baseApiPath + 'YukiReflection',
        baseApiPath + 'type/android/ComponentTypeFactory',
        baseApiPath + 'type/android/GraphicsTypeFactory',
        baseApiPath + 'type/android/ViewTypeFactory',
        baseApiPath + 'type/java/VariableTypeFactory',
        baseApiPath + 'type/defined/DefinedTypeFactory',
        baseApiPath + 'log/YLog',
        baseApiPath + 'factory/ReflectionFactory',
        baseApiPath + 'finder/members/MethodFinder',
        baseApiPath + 'finder/members/ConstructorFinder',
        baseApiPath + 'finder/members/FieldFinder',
        baseApiPath + 'finder/classes/DexClassFinder',
        baseApiPath + 'finder/classes/rules/result/MemberRulesResult',
        baseApiPath + 'finder/classes/rules/MemberRules',
        baseApiPath + 'finder/classes/rules/FieldRules',
        baseApiPath + 'finder/classes/rules/MethodRules',
        baseApiPath + 'finder/classes/rules/ConstructorRules',
        baseApiPath + 'finder/base/BaseFinder',
        baseApiPath + 'finder/base/rules/CountRules',
        baseApiPath + 'finder/base/rules/ModifierRules',
        baseApiPath + 'finder/base/rules/NameRules',
        baseApiPath + 'finder/base/rules/ObjectRules',
        baseApiPath + 'bean/VariousClass',
        baseApiPath + 'bean/CurrentClass',
        baseApiPath + 'bean/GenericClass'
    ],
    kotlinApi: [
        baseKotlinApiPath + 'KYukiReflection',
    ],
    about: [
        '/about/changelog',
        '/about/future',
        '/about/contacts',
        '/about/about'
    ]
};

export const configs = {
    dev: {
        dest: 'dist',
        port: 9000
    },
    website: {
        base: '/YukiReflection/',
        icon: '/YukiReflection/images/logo.png',
        logo: '/images/logo.png',
        title: 'Yuki Reflection',
        locales: {
            '/kotlin/en/': {
                title: 'KYuki Reflection',
                lang: 'kotlin-en-US',
                description: 'An efficient Reflection API for Java and Android built in Kotlin.Fully attach Kotlin high-order reflections.'
            },
            '/en/': {
                lang: 'en-US',
                description: 'An efficient Reflection API for Java and Android built in Kotlin'
            },
            '/kotlin/zh-cn/': {
                title: 'KYuki Reflection',
                lang: 'kotlin-zh-CN',
                description: '一个使用 Kotlin 构建的用于 Java 和 Android 平台高效反射 API - 完全附加 Kotlin 高阶反射'
            },
            '/zh-cn/': {
                lang: 'zh-CN',
                description: '一个使用 Kotlin 构建的用于 Java 和 Android 平台高效反射 API'
            }
        }
    },
    github: {
        repo: 'https://github.com/HighCapable/YukiReflection',
        branch: 'master',
        dir: 'docs-source/src'
    }
};

export const navBarItems = {
    '/kotlin/en/': [{
        text: 'Navigation',
        children: [{
            text: 'Get Started',
            children: [
                { text: 'Introduce', link: i18n.string(navigationLinks.start[0], 'kotlin/en') },
                { text: 'Quick Start', link: i18n.string(navigationLinks.start[1], 'kotlin/en') }
            ]
        }, {
            text: 'Configs',
            children: [
                { text: 'API Basic Configs', link: i18n.string(navigationLinks.config[0], 'kotlin/en') },
                { text: 'API Exception Handling', link: i18n.string(navigationLinks.config[1], 'kotlin/en') }
            ]
        }, {
            text: 'API Document',
            children: [{ text: 'Document Introduction', link: i18n.string(navigationLinks.apiDocs[0], 'kotlin/en') }, {
                text: 'Public API',
                link: i18n.string(navigationLinks.kotlinApi[0], 'kotlin/en'),
                activeMatch: i18n.string(navigationLinks.apiDocs[1], 'kotlin/en')
            }, {
                text: 'Features',
                link: i18n.string(navigationLinks.apiDocs[2], 'kotlin/en')
            }]
        }, {
            text: 'About',
            children: [
                { text: 'Changelog', link: i18n.string(navigationLinks.about[0], 'kotlin/en') },
                { text: 'Looking for Future', link: i18n.string(navigationLinks.about[1], 'kotlin/en') },
                { text: 'Contact Us', link: i18n.string(navigationLinks.about[2], 'kotlin/en') },
                { text: 'About this Document', link: i18n.string(navigationLinks.about[3], 'kotlin/en') }
            ]
        }]
    }, {
        text: 'Contact Us',
        link: i18n.string(navigationLinks.about[2], 'kotlin/en')
    }],
    '/en/': [{
        text: 'Navigation',
        children: [{
            text: 'Get Started',
            children: [
                { text: 'Introduce', link: i18n.string(navigationLinks.start[0], 'en') },
                { text: 'Quick Start', link: i18n.string(navigationLinks.start[1], 'en') }
            ]
        }, {
            text: 'Configs',
            children: [
                { text: 'API Basic Configs', link: i18n.string(navigationLinks.config[0], 'en') },
                { text: 'API Exception Handling', link: i18n.string(navigationLinks.config[1], 'en') }
            ]
        }, {
            text: 'API Document',
            children: [{ text: 'Document Introduction', link: i18n.string(navigationLinks.apiDocs[0], 'en') }, {
                text: 'Public API',
                link: i18n.string(navigationLinks.publicApi[0], 'en'),
                activeMatch: i18n.string(navigationLinks.apiDocs[1], 'en')
            }, {
                text: 'Features',
                link: i18n.string(navigationLinks.apiDocs[2], 'en')
            }]
        }, {
            text: 'About',
            children: [
                { text: 'Changelog', link: i18n.string(navigationLinks.about[0], 'en') },
                { text: 'Looking for Future', link: i18n.string(navigationLinks.about[1], 'en') },
                { text: 'Contact Us', link: i18n.string(navigationLinks.about[2], 'en') },
                { text: 'About this Document', link: i18n.string(navigationLinks.about[3], 'en') }
            ]
        }]
    }, {
        text: 'Contact Us',
        link: i18n.string(navigationLinks.about[2], 'en')
    }],
    '/kotlin/zh-cn/': [{
        text: '导航',
        children: [{
            text: '入门',
            children: [
                { text: '介绍', link: i18n.string(navigationLinks.start[0], 'kotlin/zh-cn') },
                { text: '快速开始', link: i18n.string(navigationLinks.start[1], 'kotlin/zh-cn') }
            ]
        }, {
            text: '配置',
            children: [
                { text: 'API 基本配置', link: i18n.string(navigationLinks.config[0], 'kotlin/zh-cn') },
                { text: 'API 异常处理', link: i18n.string(navigationLinks.config[1], 'kotlin/zh-cn') }
            ]
        }, {
            text: 'API 文档',
            children: [{ text: '文档介绍', link: i18n.string(navigationLinks.apiDocs[0], 'kotlin/zh-cn') }, {
                text: 'Public API',
                link: i18n.string(navigationLinks.kotlinApi[0], 'kotlin/zh-cn'),
                activeMatch: i18n.string(navigationLinks.apiDocs[1], 'kotlin/zh-cn')
            }, {
                text: '功能介绍',
                link: i18n.string(navigationLinks.apiDocs[2], 'kotlin/zh-cn')
            }]
        }, {
            text: '关于',
            children: [
                { text: '更新日志', link: i18n.string(navigationLinks.about[0], 'kotlin/zh-cn') },
                { text: '展望未来', link: i18n.string(navigationLinks.about[1], 'kotlin/zh-cn') },
                { text: '联系我们', link: i18n.string(navigationLinks.about[2], 'kotlin/zh-cn') },
                { text: '关于此文档', link: i18n.string(navigationLinks.about[3], 'kotlin/zh-cn') }
            ]
        }]
    }, {
        text: '联系我们',
        link: i18n.string(navigationLinks.about[2], 'kotlin/zh-cn')
    }],
    '/zh-cn/': [{
        text: '导航',
        children: [{
            text: '入门',
            children: [
                { text: '介绍', link: i18n.string(navigationLinks.start[0], 'zh-cn') },
                { text: '快速开始', link: i18n.string(navigationLinks.start[1], 'zh-cn') }
            ]
        }, {
            text: '配置',
            children: [
                { text: 'API 基本配置', link: i18n.string(navigationLinks.config[0], 'zh-cn') },
                { text: 'API 异常处理', link: i18n.string(navigationLinks.config[1], 'zh-cn') }
            ]
        }, {
            text: 'API 文档',
            children: [{ text: '文档介绍', link: i18n.string(navigationLinks.apiDocs[0], 'zh-cn') }, {
                text: 'Public API',
                link: i18n.string(navigationLinks.publicApi[0], 'zh-cn'),
                activeMatch: i18n.string(navigationLinks.apiDocs[1], 'zh-cn')
            }, {
                text: '功能介绍',
                link: i18n.string(navigationLinks.apiDocs[2], 'zh-cn')
            }]
        }, {
            text: '关于',
            children: [
                { text: '更新日志', link: i18n.string(navigationLinks.about[0], 'zh-cn') },
                { text: '展望未来', link: i18n.string(navigationLinks.about[1], 'zh-cn') },
                { text: '联系我们', link: i18n.string(navigationLinks.about[2], 'zh-cn') },
                { text: '关于此文档', link: i18n.string(navigationLinks.about[3], 'zh-cn') }
            ]
        }]
    }, {
        text: '联系我们',
        link: i18n.string(navigationLinks.about[2], 'zh-cn')
    }]
};

export const sideBarItems = {
    '/kotlin/en/': [{
        text: 'Get Started',
        collapsible: true,
        children: i18n.array(navigationLinks.start, '/kotlin/en')
    }, {
        text: 'Configs',
        collapsible: true,
        children: i18n.array(navigationLinks.config, '/kotlin/en')
    }, {
        text: 'API Document',
        collapsible: true,
        children: [i18n.string(navigationLinks.apiDocs[0], '/kotlin/en'), {
            text: 'Public API' + i18n.space,
            collapsible: true,
            children: i18n.array(navigationLinks.kotlinApi, '/kotlin/en')
        }, i18n.string(navigationLinks.apiDocs[2], '/kotlin/en')]
    }, {
        text: 'About',
        collapsible: true,
        children: i18n.array(navigationLinks.about, '/kotlin/en')
    }],
    '/en/': [{
        text: 'Get Started',
        collapsible: true,
        children: i18n.array(navigationLinks.start, 'en')
    }, {
        text: 'Configs',
        collapsible: true,
        children: i18n.array(navigationLinks.config, 'en')
    }, {
        text: 'API Document',
        collapsible: true,
        children: [i18n.string(navigationLinks.apiDocs[0], 'en'), {
            text: 'Public API' + i18n.space,
            collapsible: true,
            children: i18n.array(navigationLinks.publicApi, 'en')
        }, i18n.string(navigationLinks.apiDocs[2], 'en')]
    }, {
        text: 'About',
        collapsible: true,
        children: i18n.array(navigationLinks.about, 'en')
    }],
    '/kotlin/zh-cn/': [{
        text: '入门',
        collapsible: true,
        children: i18n.array(navigationLinks.start, 'kotlin/zh-cn')
    }, {
        text: '配置',
        collapsible: true,
        children: i18n.array(navigationLinks.config, 'kotlin/zh-cn')
    }, {
        text: 'API 文档',
        collapsible: true,
        children: [i18n.string(navigationLinks.apiDocs[0], 'kotlin/zh-cn'), {
            text: 'Public API' + i18n.space,
            collapsible: true,
            children: i18n.array(navigationLinks.kotlinApi, 'kotlin/zh-cn')
        }, i18n.string(navigationLinks.apiDocs[2], 'kotlin/zh-cn')]
    }, {
        text: '关于',
        collapsible: true,
        children: i18n.array(navigationLinks.about, 'kotlin/zh-cn')
    }],
    '/zh-cn/': [{
        text: '入门',
        collapsible: true,
        children: i18n.array(navigationLinks.start, 'zh-cn')
    }, {
        text: '配置',
        collapsible: true,
        children: i18n.array(navigationLinks.config, 'zh-cn')
    }, {
        text: 'API 文档',
        collapsible: true,
        children: [i18n.string(navigationLinks.apiDocs[0], 'zh-cn'), {
            text: 'Public API' + i18n.space,
            collapsible: true,
            children: i18n.array(navigationLinks.publicApi, 'zh-cn')
        }, i18n.string(navigationLinks.apiDocs[2], 'zh-cn')]
    }, {
        text: '关于',
        collapsible: true,
        children: i18n.array(navigationLinks.about, 'zh-cn')
    }]
};
import{_ as r,r as l,o as d,c as u,a as e,b as n,w as t,e as i,d as o}from"./app.3cb5b3a5.js";const p={},h=i('<h1 id="introduce" tabindex="-1"><a class="header-anchor" href="#introduce" aria-hidden="true">#</a> Introduce</h1><blockquote><p><code>YukiReflection</code> is a Reflection API based on the Android platform.</p></blockquote><h2 id="background" tabindex="-1"><a class="header-anchor" href="#background" aria-hidden="true">#</a> Background</h2><p>This is a set of simple and efficient Reflection API rebuilt based on <code>Java</code> native Reflection API using <code>Kotlin</code>.</p>',4),_=e("code",null,"YukiReflection",-1),m=o(" is also the core functionality that "),f={href:"https://github.com/fankes/YukiHookAPI",target:"_blank",rel:"noopener noreferrer"},y=o("YukiHookAPI"),A=o(" is using."),b=o("The name is taken from "),g={href:"https://www.bilibili.com/bangumi/play/ss5016",target:"_blank",rel:"noopener noreferrer"},k=o('"\u3082\u3082\u304F\u308A" heroine Yuki Kurihara'),C=o("."),D=e("h2",{id:"usage",tabindex:"-1"},[e("a",{class:"header-anchor",href:"#usage","aria-hidden":"true"},"#"),o(" Usage")],-1),B=e("p",null,[e("code",null,"YukiReflection"),o(" is fully built with "),e("code",null,"Kotlin"),o(),e("code",null,"lambda"),o(" syntax.")],-1),F=o("It can replace "),v={href:"https://www.oracle.com/technical-resources/articles/java/javareflection.html",target:"_blank",rel:"noopener noreferrer"},w=o("Java's native Reflection API"),I=o(" and implement a more complete reflection solution in a more human-friendly language."),x=i('<h2 id="language-requirement" tabindex="-1"><a class="header-anchor" href="#language-requirement" aria-hidden="true">#</a> Language Requirement</h2><p>Please use <code>Kotlin</code>, the code composition of the API part is also compatible with <code>Java</code>, but the implementation of the basic reflection scene <strong>may not be used at all</strong>.</p><p>All Demo sample codes in the document will be described using <code>Kotlin</code>, if you don\u2019t know how to use <code>Kotlin</code> at all, you may not be able to use <code>YukiReflection</code>.</p><h2 id="source-of-inspiration" tabindex="-1"><a class="header-anchor" href="#source-of-inspiration" aria-hidden="true">#</a> Source of Inspiration</h2>',4),R=e("code",null,"YukiReflection",-1),P=o(" was originally the core function integrated in the "),Y={href:"https://github.com/fankes/YukiHookAPI",target:"_blank",rel:"noopener noreferrer"},S=o("YukiHookAPI"),K=o(" project, and now it is decoupled so that this Reflection API can be used in any Android platform project."),j=e("p",null,"Now, we only need to write a small amount of code to implement a simple reflection call.",-1),q=e("p",null,[o("With "),e("code",null,"Kotlin"),o(" elegant "),e("code",null,"lambda"),o(" and "),e("code",null,"YukiReflection"),o(", you can make your reflection logic more beautiful and clear.")],-1),N=e("blockquote",null,[e("p",null,"The following example")],-1),G=e("div",{class:"language-kotlin ext-kt line-numbers-mode"},[e("pre",{class:"shiki",style:{"background-color":"#22272e"}},[e("code",null,[e("span",{class:"line"},[e("span",{style:{color:"#96D0FF"}},'"android.os.SystemProperties"'),e("span",{style:{color:"#ADBAC7"}},".toClass()")]),o(`
`),e("span",{class:"line"},[e("span",{style:{color:"#ADBAC7"}},"    .method {")]),o(`
`),e("span",{class:"line"},[e("span",{style:{color:"#ADBAC7"}},"        name "),e("span",{style:{color:"#F47067"}},"="),e("span",{style:{color:"#ADBAC7"}}," "),e("span",{style:{color:"#96D0FF"}},'"get"')]),o(`
`),e("span",{class:"line"},[e("span",{style:{color:"#ADBAC7"}},"        param("),e("span",{style:{color:"#F69D50"}},"StringClass"),e("span",{style:{color:"#ADBAC7"}},", "),e("span",{style:{color:"#F69D50"}},"StringClass"),e("span",{style:{color:"#ADBAC7"}},")")]),o(`
`),e("span",{class:"line"},[e("span",{style:{color:"#ADBAC7"}},"    }.get().call("),e("span",{style:{color:"#96D0FF"}},'"ro.system.build.fingerprint"'),e("span",{style:{color:"#ADBAC7"}},", "),e("span",{style:{color:"#96D0FF"}},'"none"'),e("span",{style:{color:"#ADBAC7"}},")")]),o(`
`),e("span",{class:"line"})])]),e("div",{class:"line-numbers","aria-hidden":"true"},[e("div",{class:"line-number"}),e("div",{class:"line-number"}),e("div",{class:"line-number"}),e("div",{class:"line-number"}),e("div",{class:"line-number"})])],-1),H=e("div",{class:"language-kotlin ext-kt line-numbers-mode"},[e("pre",{class:"shiki",style:{"background-color":"#22272e"}},[e("code",null,[e("span",{class:"line"},[e("span",{style:{color:"#F69D50"}},"Class"),e("span",{style:{color:"#ADBAC7"}},".forName("),e("span",{style:{color:"#96D0FF"}},'"android.os.SystemProperties"'),e("span",{style:{color:"#ADBAC7"}},")")]),o(`
`),e("span",{class:"line"},[e("span",{style:{color:"#ADBAC7"}},"    .getDeclaredMethod("),e("span",{style:{color:"#96D0FF"}},'"get"'),e("span",{style:{color:"#ADBAC7"}},", String::"),e("span",{style:{color:"#6CB6FF"}},"class"),e("span",{style:{color:"#ADBAC7"}},".java, String::"),e("span",{style:{color:"#6CB6FF"}},"class"),e("span",{style:{color:"#ADBAC7"}},".java)")]),o(`
`),e("span",{class:"line"},[e("span",{style:{color:"#ADBAC7"}},"    ."),e("span",{style:{color:"#6CB6FF"}},"apply"),e("span",{style:{color:"#ADBAC7"}}," { isAccessible "),e("span",{style:{color:"#F47067"}},"="),e("span",{style:{color:"#ADBAC7"}}," "),e("span",{style:{color:"#6CB6FF"}},"true"),e("span",{style:{color:"#ADBAC7"}}," }")]),o(`
`),e("span",{class:"line"},[e("span",{style:{color:"#ADBAC7"}},"    .invoke("),e("span",{style:{color:"#6CB6FF"}},"null"),e("span",{style:{color:"#ADBAC7"}},", "),e("span",{style:{color:"#96D0FF"}},'"ro.system.build.fingerprint"'),e("span",{style:{color:"#ADBAC7"}},", "),e("span",{style:{color:"#96D0FF"}},'"none"'),e("span",{style:{color:"#ADBAC7"}},")")]),o(`
`),e("span",{class:"line"})])]),e("div",{class:"line-numbers","aria-hidden":"true"},[e("div",{class:"line-number"}),e("div",{class:"line-number"}),e("div",{class:"line-number"}),e("div",{class:"line-number"})])],-1);function J(T,V){const s=l("ExternalLinkIcon"),a=l("CodeGroupItem"),c=l("CodeGroup");return d(),u("div",null,[h,e("p",null,[_,m,e("a",f,[y,n(s)]),A]),e("p",null,[b,e("a",g,[k,n(s)]),C]),D,B,e("p",null,[F,e("a",v,[w,n(s)]),I]),x,e("p",null,[R,P,e("a",Y,[S,n(s)]),K]),j,q,N,n(c,null,{default:t(()=>[n(a,{title:"Yuki Reflection"},{default:t(()=>[G]),_:1}),n(a,{title:"Java Reflection"},{default:t(()=>[H]),_:1})]),_:1})])}const L=r(p,[["render",J],["__file","home.html.vue"]]);export{L as default};
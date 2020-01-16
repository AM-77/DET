const machine_infos = (head, features) => `<div class="machine-infos">${head}${features}</div>`
const head = (machine_name) => `<div class="head"><h2 class="machine-name">${machine_name}</h2></div>`
const features = (features_table) => {
    let features = `<div class="info">`
    features_table.forEach(feature => {
        features += info(feature.features, feature.title)
    })
    features += `</div>`
    return features
}
const machin_img = (img_name) => `<div class="machine-img"><img src="${location.origin}/${img_name}" alt="machine"></div>`
const info = (features, type) => {
    let html_features = `<div class="${type}"><h2 class="column-name">${type}</h2><div class="features">`
    features.forEach(f => { html_features += `<div class="feature"><span class="title">${f.feature}</span><span class="value">${f.value}</span></div>` })
    html_features += `</div></div>`
    return html_features
}

export const format_machine = (machine_name, machine) => {
    let _head = head(machine_name)
    let _features = features(machine.columns)
    let _machine_infos = machine_infos(_head, _features)
    let _machin_img = machin_img(machine.imageName)
    let _machine = `<div class="machine-wrapper">${_machine_infos}${_machin_img}</div>`
    return _machine
}
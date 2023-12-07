import request from '@/utils/request'

// 查询岗位列表
export function listPost(query) {
  return request({
    url: '/system/tenant/list',
    method: 'get',
    params: query
  })
}

// 查询岗位详细
export function getPost(id) {
  return request({
    url: '/system/tenant/' + id,
    method: 'get'
  })
}

// 新增岗位
export function addPost(data) {
  return request({
    url: '/system/tenant',
    method: 'post',
    data: data
  })
}

// 修改岗位
export function updatePost(data) {
  return request({
    url: '/system/tenant',
    method: 'put',
    data: data
  })
}

// 删除岗位
export function delPost(id) {
  return request({
    url: '/system/tenant/' + id,
    method: 'delete'
  })
}

'use strict';

// post-model.js - A mongoose model
// 
// See http://mongoosejs.com/docs/models.html
// for more of what you can do here.

const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const postSchema = new Schema({
  _id: String,
  text: { type: String, required: true },
  parts: [String],
  accessedBy: [Schema.Types.ObjectId],
  createdAt: { type: Date, 'default': Date.now },
  updatedAt: { type: Date, 'default': Date.now }
});


const postModel = mongoose.model('post', postSchema);

module.exports = postModel;
